/*
 * Copyright (C) 2012 The Android Open Source Project
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

package com.google.android.vending.expansion.downloader.impl;

import com.google.android.vending.expansion.downloader.Helpers;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

/**
 * This class wraps NotificationCompat to give us similar progress functionality on Donut-Honeycomb.
 */
public class V4CustomNotificationBuilder extends NotificationCompat.Builder {

    public V4CustomNotificationBuilder(Context context) {
        super(context);
    }

    @Override
    public NotificationCompat.Builder setProgress(int max, int progress, boolean indeterminate) {
        mTotalBytes = max;
        mCurrentBytes = progress;
        return super.setProgress(max, progress, indeterminate);
    }

    @Override
    public NotificationCompat.Builder setContentInfo(CharSequence info) {
        mContentInfo = info;
        return super.setContentInfo(info);
    }

    int mTotalBytes = -1;
    int mCurrentBytes = -1;
    int mIcon;
    CharSequence mContentInfo = "";

    @Override
    public Notification build() {

        if (android.os.Build.VERSION.SDK_INT > 10) {
            // only matters for Honeycomb
            setOnlyAlertOnce(true);
        }

        // Build the RemoteView object
        RemoteViews expandedView = new RemoteViews(
                mContext.getPackageName(),
                Helpers.getLayoutResource(mContext, "status_bar_ongoing_event_progress_bar"));

        expandedView.setTextViewText(Helpers.getIdResource(mContext, "title"), mContentTitle);
        // look at strings
        expandedView.setViewVisibility(Helpers.getIdResource(mContext, "description"), View.VISIBLE);
        expandedView.setTextViewText(Helpers.getIdResource(mContext, "description"),
                Helpers.getDownloadProgressString(this.mCurrentBytes, mTotalBytes));
        expandedView.setViewVisibility(Helpers.getIdResource(mContext, "progress_bar_frame"), View.VISIBLE);
        expandedView.setProgressBar(Helpers.getIdResource(mContext, "progress_bar"),
                (int) (mTotalBytes >> 8),
                (int) (mCurrentBytes >> 8),
                mTotalBytes <= 0);
        expandedView.setViewVisibility(Helpers.getIdResource(mContext, "time_remaining"), View.VISIBLE);
        expandedView.setTextViewText(
                Helpers.getIdResource(mContext, "time_remaining"),
                mContentInfo);
        expandedView.setTextViewText(Helpers.getIdResource(mContext, "progress_text"),
                Helpers.getDownloadProgressPercent(mCurrentBytes, mTotalBytes));
        expandedView.setImageViewResource(Helpers.getIdResource(mContext, "appIcon"), mIcon);

        Notification n = super.build();
        n.contentView = expandedView;
        return n;
    }
}
