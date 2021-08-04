package com.taotao.cloud.data.mybatis.plus.conditions.update;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.taotao.cloud.common.utils.StrHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * 修改构造器
 * <p>
 * 1, 对nested、eq、ne、gt、ge、lt、le、in、*like*、 等方法 进行条件判断，null 或 "" 字段不加入查询 2，对*like*相关方法的参数 %和_
 * 符号进行转义，便于模糊查询
 *
 * @author zuihou
 * @date Created on 2019/5/27 17:15
 */
public class LbuWrapper<T> extends AbstractLambdaWrapper<T, LbuWrapper<T>>
	implements Update<LbuWrapper<T>, SFunction<T, ?>> {

	private static final long serialVersionUID = -4194344880194881367L;
	/**
	 * SQL 更新字段内容，例如：name='1', age=2
	 */
	private final List<String> sqlSet;

	private SharedString paramAlias;

	/**
	 * 不建议直接 new 该实例，使用 Wrappers.lambdaUpdate()
	 */
	public LbuWrapper() {
		// 如果无参构造函数，请注意实体 NULL 情况 SET 必须有否则 SQL 异常
		this((T) null);
	}

	/**
	 * 不建议直接 new 该实例，使用 Wrappers.lambdaUpdate(entity)
	 */
	public LbuWrapper(T entity) {
		super.setEntity(entity);
		super.initNeed();
		this.sqlSet = new ArrayList<>();
	}

	/**
	 * 不建议直接 new 该实例，使用 Wrappers.lambdaUpdate(entity)
	 */
	public LbuWrapper(Class<T> entityClass) {
		super.setEntityClass(entityClass);
		super.initNeed();
		this.sqlSet = new ArrayList<>();
	}

	/**
	 * 不建议直接 new 该实例，使用 Wrappers.lambdaUpdate(...)
	 */
	private LbuWrapper(T entity, Class<T> entityClass, List<String> sqlSet,
		AtomicInteger paramNameSeq,
		Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments,
		SharedString paramAlias,
		SharedString lastSql, SharedString sqlComment, SharedString sqlFirst) {
		super.setEntity(entity);
		super.setEntityClass(entityClass);
		this.sqlSet = sqlSet;
		this.paramNameSeq = paramNameSeq;
		this.paramNameValuePairs = paramNameValuePairs;
		this.expression = mergeSegments;
		this.paramAlias = paramAlias;
		this.lastSql = lastSql;
		this.sqlComment = sqlComment;
		this.sqlFirst = sqlFirst;
	}

	/**
	 * 空值校验 传入空字符串("")时， 视为： 字段名 = ""
	 *
	 * @param val 参数值
	 * @author zuihou
	 */
	private static boolean checkCondition(Object val) {
		return val != null;
	}


	@Override
	public LbuWrapper<T> set(SFunction<T, ?> column, Object val) {
		return Update.super.set(column, val);
	}

	@Override
	public LbuWrapper<T> set(boolean condition,
		SFunction<T, ?> column, Object val) {
		//		return maybeDo(condition, () -> {
//			String sql = formatParam(mapping, val);
//			sqlSet.add(columnToString(column) + Constants.EQUALS + sql);
//		});
		return null;
	}

	@Override
	public LbuWrapper<T> setSql(String sql) {
		return Update.super.setSql(sql);
	}

	@Override
	public LbuWrapper<T> setSql(boolean condition, String sql) {
		if (condition && StringUtils.isNotBlank(sql)) {
			sqlSet.add(sql);
		}
		return typedThis;
	}

	@Override
	public String getSqlSet() {
		if (CollectionUtils.isEmpty(sqlSet)) {
			return null;
		}
		return String.join(StringPool.COMMA, sqlSet);
	}

	@Override
	protected LbuWrapper<T> instance() {
		return new LbuWrapper<>(getEntity(), getEntityClass(), null, paramNameSeq,
			paramNameValuePairs,
			new MergeSegments(), paramAlias, SharedString.emptyString(), SharedString.emptyString(),
			SharedString.emptyString());
	}

	@Override
	public void clear() {
		super.clear();
		sqlSet.clear();
	}

	@Override
	public LbuWrapper<T> and(
		Consumer<LbuWrapper<T>> consumer) {
		return super.and(consumer);
	}

	@Override
	public LbuWrapper<T> or(
		Consumer<LbuWrapper<T>> consumer) {
		return super.or(consumer);
	}

	@Override
	public LbuWrapper<T> nested(Consumer<LbuWrapper<T>> consumer) {
		final LbuWrapper<T> instance = instance();
		consumer.accept(instance);
		if (!instance.isEmptyOfWhere()) {
//			appendSqlSegments(APPLY, instance);
		}
		return this;
	}

	@Override
	public LbuWrapper<T> not(
		Consumer<LbuWrapper<T>> consumer) {
		return super.not(consumer);
	}


	@Override
	public <V> LbuWrapper<T> allEq(
		Map<SFunction<T, ?>, V> params) {
		return super.allEq(params);
	}

	@Override
	public <V> LbuWrapper<T> allEq(
		Map<SFunction<T, ?>, V> params, boolean null2IsNull) {
		return super.allEq(params, null2IsNull);
	}

	@Override
	public <V> LbuWrapper<T> allEq(
		BiPredicate<SFunction<T, ?>, V> filter,
		Map<SFunction<T, ?>, V> params) {
		return super.allEq(filter, params);
	}

	@Override
	public <V> LbuWrapper<T> allEq(
		BiPredicate<SFunction<T, ?>, V> filter,
		Map<SFunction<T, ?>, V> params, boolean null2IsNull) {
		return super.allEq(filter, params, null2IsNull);
	}

	@Override
	public LbuWrapper<T> eq(SFunction<T, ?> column, Object val) {
		return super.eq(checkCondition(val), column, val);
	}

	@Override
	public LbuWrapper<T> ne(SFunction<T, ?> column, Object val) {
		return super.ne(checkCondition(val), column, val);
	}

	@Override
	public LbuWrapper<T> gt(SFunction<T, ?> column, Object val) {
		return super.gt(checkCondition(val), column, val);
	}

	@Override
	public LbuWrapper<T> ge(SFunction<T, ?> column, Object val) {
		return super.ge(checkCondition(val), column, val);
	}

	@Override
	public LbuWrapper<T> lt(SFunction<T, ?> column, Object val) {
		return super.lt(checkCondition(val), column, val);
	}

	@Override
	public LbuWrapper<T> le(SFunction<T, ?> column, Object val) {
		return super.le(checkCondition(val), column, val);
	}

	@Override
	public LbuWrapper<T> between(SFunction<T, ?> column, Object val1, Object val2) {
		return super.between(column, val1, val2);
	}

	@Override
	public LbuWrapper<T> notBetween(
		SFunction<T, ?> column, Object val1, Object val2) {
		return super.notBetween(column, val1, val2);
	}

	@Override
	public LbuWrapper<T> like(SFunction<T, ?> column, Object val) {
		return super.like(checkCondition(val), column, StrHelper.keywordConvert(val.toString()));
	}

	@Override
	public LbuWrapper<T> notLike(SFunction<T, ?> column, Object val) {
		return super.notLike(checkCondition(val), column, StrHelper.keywordConvert(val.toString()));
	}

	@Override
	public LbuWrapper<T> likeLeft(SFunction<T, ?> column, Object val) {
		return super.likeLeft(checkCondition(val), column,
			StrHelper.keywordConvert(val.toString()));
	}

	@Override
	public LbuWrapper<T> likeRight(SFunction<T, ?> column, Object val) {
		return super.likeRight(checkCondition(val), column,
			StrHelper.keywordConvert(val.toString()));
	}

	@Override
	public LbuWrapper<T> isNull(SFunction<T, ?> column) {
		return super.isNull(column);
	}

	@Override
	public LbuWrapper<T> isNotNull(SFunction<T, ?> column) {
		return super.isNotNull(column);
	}

	@Override
	public LbuWrapper<T> in(SFunction<T, ?> column, Collection<?> coll) {
		return super.in(coll != null && !coll.isEmpty(), column, coll);
	}

	@Override
	public LbuWrapper<T> in(SFunction<T, ?> column, Object... values) {
		return super.in(values != null && values.length > 0, column, values);
	}

	@Override
	public LbuWrapper<T> in(boolean condition,
		SFunction<T, ?> column, Object... values) {
		return super.in(condition, column, values);
	}

	@Override
	public LbuWrapper<T> notIn(SFunction<T, ?> column, Collection<?> coll) {
		return super.notIn(column, coll);
	}

	@Override
	public LbuWrapper<T> notIn(SFunction<T, ?> column, Object... value) {
		return super.notIn(column, value);
	}

	@Override
	public LbuWrapper<T> notIn(boolean condition,
		SFunction<T, ?> column, Object... values) {
		return super.notIn(condition, column, values);
	}

	@Override
	public LbuWrapper<T> inSql(SFunction<T, ?> column, String inValue) {
		return super.inSql(column, inValue);
	}

	@Override
	public LbuWrapper<T> notInSql(SFunction<T, ?> column, String inValue) {
		return super.notInSql(column, inValue);
	}

	@Override
	public LbuWrapper<T> groupBy(SFunction<T, ?> column) {
		return super.groupBy(column);
	}

	@Override
	public LbuWrapper<T> groupBy(
		SFunction<T, ?>... columns) {
		return super.groupBy(columns);
	}

	@Override
	public LbuWrapper<T> orderByAsc(
		SFunction<T, ?> column) {
		return super.orderByAsc(column);
	}

	@Override
	public LbuWrapper<T> orderByAsc(
		SFunction<T, ?>... columns) {
		return super.orderByAsc(columns);
	}

	@Override
	public LbuWrapper<T> orderByAsc(boolean condition,
		SFunction<T, ?>... columns) {
		return super.orderByAsc(condition, columns);
	}

	@Override
	public LbuWrapper<T> orderByDesc(
		SFunction<T, ?> column) {
		return super.orderByDesc(column);
	}

	@Override
	public LbuWrapper<T> orderByDesc(
		SFunction<T, ?>... columns) {
		return super.orderByDesc(columns);
	}

	@Override
	public LbuWrapper<T> orderByDesc(boolean condition,
		SFunction<T, ?>... columns) {
		return super.orderByDesc(condition, columns);
	}

	@Override
	public LbuWrapper<T> having(String sqlHaving, Object... params) {
		return super.having(sqlHaving, params);
	}

	@Override
	public LbuWrapper<T> func(
		Consumer<LbuWrapper<T>> consumer) {
		return super.func(consumer);
	}

	@Override
	public LbuWrapper<T> or() {
		return super.or();
	}

	@Override
	public LbuWrapper<T> apply(String applySql, Object... value) {
		return super.apply(applySql, value);
	}

	@Override
	public LbuWrapper<T> last(String lastSql) {
		return super.last(lastSql);
	}

	@Override
	public LbuWrapper<T> comment(String comment) {
		return super.comment(comment);
	}

	@Override
	public LbuWrapper<T> first(String firstSql) {
		return super.first(firstSql);
	}

	@Override
	public LbuWrapper<T> exists(String existsSql) {
		return super.exists(existsSql);
	}

	@Override
	public LbuWrapper<T> notExists(String existsSql) {
		return super.notExists(existsSql);
	}
}