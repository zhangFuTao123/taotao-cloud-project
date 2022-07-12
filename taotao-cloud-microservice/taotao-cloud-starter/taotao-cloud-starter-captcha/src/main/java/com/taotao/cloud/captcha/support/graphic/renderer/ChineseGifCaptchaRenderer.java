/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.captcha.support.graphic.renderer;

import com.taotao.cloud.captcha.support.core.definition.enums.CaptchaCategory;
import com.taotao.cloud.captcha.support.graphic.definition.AbstractGifGraphicRenderer;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * <p>Description: 中文Gif类型验证码绘制器 </p>
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-12 12:54:45
 */
@Component
public class ChineseGifCaptchaRenderer extends AbstractGifGraphicRenderer {

    @Override
    public String getCategory() {
        return CaptchaCategory.CHINESE_GIF.getConstant();
    }

    @Override
    protected String[] getDrawCharacters() {
        return this.getWordCharacters();
    }

    @Override
    protected Font getFont() {
        return this.getResourceProvider().getChineseFont();
    }
}