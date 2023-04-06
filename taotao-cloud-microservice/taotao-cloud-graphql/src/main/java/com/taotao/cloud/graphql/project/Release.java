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

package com.taotao.cloud.graphql.project;

public class Release {

    private String version;

    private ReleaseStatus status;

    private String referenceDocUrl;

    private String apiDocUrl;

    private boolean current;

    public Release() {}

    public Release(Project project, String version, ReleaseStatus status) {
        this.version = version;
        this.status = status;
        this.apiDocUrl = String.format("https://docs.spring.io/%s/docs/%s/javadoc-api/", project.getSlug(), version);
        this.referenceDocUrl =
                String.format("https://docs.spring.io/%s/docs/%s/reference/html/", project.getSlug(), version);
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ReleaseStatus getStatus() {
        return this.status;
    }

    public void setStatus(ReleaseStatus status) {
        this.status = status;
    }

    public String getReferenceDocUrl() {
        return this.referenceDocUrl;
    }

    public void setReferenceDocUrl(String referenceDocUrl) {
        this.referenceDocUrl = referenceDocUrl;
    }

    public String getApiDocUrl() {
        return this.apiDocUrl;
    }

    public void setApiDocUrl(String apiDocUrl) {
        this.apiDocUrl = apiDocUrl;
    }

    public boolean isCurrent() {
        return this.current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
