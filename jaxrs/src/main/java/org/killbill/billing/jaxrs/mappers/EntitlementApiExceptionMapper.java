/*
 * Copyright 2010-2013 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.jaxrs.mappers;

import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.killbill.billing.ErrorCode;
import org.killbill.billing.entitlement.api.EntitlementApiException;

@Singleton
@Provider
public class EntitlementApiExceptionMapper extends ExceptionMapperBase implements ExceptionMapper<EntitlementApiException> {

    private final UriInfo uriInfo;

    public EntitlementApiExceptionMapper(@Context final UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @Override
    public Response toResponse(final EntitlementApiException exception) {
        if (exception.getCode() == ErrorCode.SUB_CANCEL_BAD_STATE.getCode()) {
            return buildInternalErrorResponse(exception, uriInfo);
        } else if (exception.getCode() == ErrorCode.SUB_CHANGE_NON_ACTIVE.getCode()) {
            return buildInternalErrorResponse(exception, uriInfo);
        } else if (exception.getCode() == ErrorCode.SUB_INVALID_SUBSCRIPTION_ID.getCode()) {
            return buildNotFoundResponse(exception, uriInfo);
        } else if (exception.getCode() == ErrorCode.SUB_INVALID_SUBSCRIPTION_EXTERNAL_KEY.getCode()) {
            return buildNotFoundResponse(exception, uriInfo);
        } else {
            return fallback(exception, uriInfo);
        }
    }
}
