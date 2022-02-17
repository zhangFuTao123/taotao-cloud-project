package com.taotao.cloud.redis.delay.listener;

import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.redis.delay.message.FastJsonCodec;
import com.taotao.cloud.redis.delay.message.RedissonMessage;
import com.taotao.cloud.redis.delay.support.ThreadFactoryCreator;
import org.openjdk.nashorn.internal.objects.annotations.Getter;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisException;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;


public class BatchRedissonListenerContainer extends AbstractRedissonListenerContainer {

    private final String fetchScript;

    private final int maxFetch;

	public int getMaxFetch() {
		return maxFetch;
	}

	public BatchRedissonListenerContainer(ContainerProperties containerProperties, int maxFetch) {
        super(containerProperties);
        Assert.isTrue(maxFetch > 0, "maxFetch must be greater than 0");
        this.maxFetch = maxFetch;
        this.fetchScript = "local expiredValues = redis.call('lrange', KEYS[1], 0, ARGV[1]); if #expiredValues > 0 then redis.call('ltrim', KEYS[1], ARGV[2], -1); end; return expiredValues;";
        this.setTaskExecutor(new SimpleAsyncTaskExecutor(ThreadFactoryCreator.create("RedissonBatchConsumeThread")));
    }

    private AsyncMessageProcessingConsumer takeMessageTask;

    @Override
    protected void doStart() {
        this.takeMessageTask = new AsyncMessageProcessingConsumer();
        this.getTaskExecutor().execute(this.takeMessageTask);
    }

    @Override
    protected void doStop() {
        this.takeMessageTask.stop();
    }

    private final class AsyncMessageProcessingConsumer implements Runnable {

        private volatile Thread currentThread = null;

        private volatile ConsumerStatus status = ConsumerStatus.CREATED;

        @Override
        public void run() {
            if (this.status != ConsumerStatus.CREATED) {
	            LogUtil.info("consumer currentThread [{}] will exit, because consumer status is {},expected is CREATED", this.currentThread.getName(), this.status);
                return;
            }
            this.currentThread = Thread.currentThread();
            this.status = ConsumerStatus.RUNNING;
            final long maxWaitMillis = 100;
            long emptyFetchTimes = 0;
            for (; ; ) {
                try {
                    List<RedissonMessage> messageList = this.fetch();
                    if (messageList == null || messageList.isEmpty()) {
                        long delay = ++emptyFetchTimes * 5;
                        delay = Math.min(delay, maxWaitMillis);
                        Thread.sleep(delay);
                    } else {
                        //reset counting
                        emptyFetchTimes = 0;
                        BatchRedissonMessageListenerAdapter redissonListener = (BatchRedissonMessageListenerAdapter) BatchRedissonListenerContainer.this.getRedissonListener();
                        redissonListener.onMessage(messageList);
                    }
                } catch (InterruptedException | RedisException e) {
                    //ignore
                } catch (Exception e) {
                    LogUtil.error("error occurred while take message from redisson", e);
                }
                if (this.status == ConsumerStatus.STOPPED) {
	                LogUtil.info("consumer currentThread [{}] will exit, because of STOPPED status", this.currentThread.getName());
                    break;
                }
            }
            this.currentThread = null;
        }

        private List<RedissonMessage> fetch() {
            final String queue = BatchRedissonListenerContainer.this.getContainerProperties().getQueue();
            final RedissonClient redissonClient = BatchRedissonListenerContainer.this.getRedissonClient();
            final int fetchCount = BatchRedissonListenerContainer.this.maxFetch;
            final int searchEndIndex = fetchCount - 1;
            return (List<RedissonMessage>) redissonClient.getScript(FastJsonCodec.INSTANCE).eval(RScript.Mode.READ_WRITE, BatchRedissonListenerContainer.this.fetchScript, RScript.ReturnType.MULTI, Collections.singletonList(queue), searchEndIndex, fetchCount);
        }

        private void stop() {
            if (this.currentThread != null) {
                this.status = ConsumerStatus.STOPPED;
                this.currentThread.interrupt();
            }
        }
    }

}