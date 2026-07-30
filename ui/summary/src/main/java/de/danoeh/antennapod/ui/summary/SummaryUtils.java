package de.danoeh.antennapod.ui.summary;

import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import de.danoeh.antennapod.model.feed.FeedMedia;

public class SummaryUtils {
    private static final String TAG = "SummaryUtils";

    public static String loadSummary(FeedMedia media) {
        if (media == null || media.getSummaryFileUrl() == null) {
            return null;
        }

        File summaryFile = new File(media.getSummaryFileUrl());
        if (!summaryFile.exists()) {
            return null;
        }

        try {
            String summary = FileUtils.readFileToString(summaryFile, "UTF-8");
            if (summary != null && !summary.trim().isEmpty()) {
                return summary;
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to read summary file: " + summaryFile.getAbsolutePath(), e);
        }

        return null;
    }

    public static boolean hasSummary(FeedMedia media) {
        if (media == null || media.getSummaryFileUrl() == null) {
            return false;
        }

        File summaryFile = new File(media.getSummaryFileUrl());
        return summaryFile.exists() && summaryFile.length() > 0;
    }
}
