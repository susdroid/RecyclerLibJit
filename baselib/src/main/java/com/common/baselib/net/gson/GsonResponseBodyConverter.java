/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.common.baselib.net.gson;

import com.common.baselib.net.base.ResponseBaseBean;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by cyq on 16/8/16.
 * 目的
 * 1.为了统一处理返回值的状态,在接口出现问题的时候,统一抛出异常
 */

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;


    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            T t = adapter.read(jsonReader);
            ResponseBaseBean bean = (ResponseBaseBean) t;
            if (!isSuccess(bean.getR())) {
                throw new APIException(bean.getR(), bean.getMsg());
            }
            return t;
        } finally {
            value.close();
        }
    }

    public boolean isSuccess(int targetValue) {
        int a = targetValue;
        if (a >= 0)
            return true;
        else
            return false;
    }
}
