/*
 * Copyright 2010-2011 Ning, Inc.
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

package com.ning.billing.entitlement.events;

import org.joda.time.DateTime;

import java.util.UUID;

public interface EventLifecycle {

    public enum EventLifecycleState {
        AVAILABLE,
        IN_PROCESSING,
        PROCESSED
    }

    public long getActiveVersion();

    public void setActiveVersion(long activeVersion);

    public boolean isActive();

    public void deactivate();

    public void reactivate();

    public UUID getOwner();

    public void setOwner(UUID owner);

    public DateTime getNextAvailableDate();

    public void setNextAvailableDate(DateTime dateTime);

    public EventLifecycleState getProcessingState();

    public void setProcessingState(EventLifecycleState procesingState);

    public boolean isAvailableForProcessing(DateTime now);
}
