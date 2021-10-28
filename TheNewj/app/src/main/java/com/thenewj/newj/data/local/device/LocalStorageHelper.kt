package com.thenewj.newj.data.local.device

import android.content.Context
import android.os.Environment

import java.io.File

object LocalStorageHelper {
    lateinit var appDataLocation: String
    var FOLDER_INBOUND = "Inbound/"
    var FOLDER_PHOTOS = "Photos/"
    var FOLDER_ATTENDANCE = "Attendance/"
    var FOLDER_VISITOR = "Visitor/"
    var FOLDER_SNAPS = "Snaps/"
    var FOLDER_BATCHES = "Batches/"

    var FOLDER_OUTBOUND = "Outbound/"
    var FOLDER_STANDARDIMAGE = "StandardImage/"
    var FOLDER_STORES = "Stores/"
    var FOLDER_USERS = "Users/"
    var FOLDER_ACTIVITIES = "Activities/"
    var FOLDER_MEDIA = "Media/"
    var FOLDER_ICONS = "Icons/"
    var FOLDER_JSON = "Json/"
    var FOLDER_QUESTIONTEMPLATE = "QuestionTemplate/"
    var FOLDER_CSV = "Csv/"
    var FOLDER_CONFIG = "Config/"
    var FOLDER_LANGUAGE = "Language/"

    val folderInbound: String
        get() = "$appDataLocation/$FOLDER_INBOUND"

    val folderPhotos: String
        get() = "$folderInbound$FOLDER_PHOTOS"

    val folderAttendance: String
        get() = "$folderPhotos$FOLDER_ATTENDANCE"

    val folderVisitor: String
        get() = "$folderPhotos$FOLDER_VISITOR"

    val folderSnaps: String
        get() = "$folderPhotos$FOLDER_SNAPS"

    val folderBatches: String
        get() = "$folderInbound$FOLDER_BATCHES"

    val folderOutbound: String
        get() = "$appDataLocation/$FOLDER_OUTBOUND"

    val folderStandardimage: String
        get() = "$folderOutbound$FOLDER_STANDARDIMAGE"

    val folderStores: String
        get() = "$folderStandardimage$FOLDER_STORES"

    val folderUsers: String
        get() = "$folderStandardimage$FOLDER_USERS"

    val folderActivities: String
        get() = "$folderStandardimage$FOLDER_ACTIVITIES"

    val folderMedia: String
        get() = "$folderOutbound$FOLDER_MEDIA"

    val folderIcons: String
        get() = "$folderOutbound$FOLDER_ICONS"

    val folderJson: String
        get() = "$folderOutbound$FOLDER_JSON"

    val folderQuestiontemplate: String
        get() = "$folderJson$FOLDER_QUESTIONTEMPLATE"

    val folderCsv: String
        get() = "$folderOutbound$FOLDER_CSV"

    val folderConfig: String
        get() = "$folderJson$FOLDER_CONFIG"

    val folderLanguage: String
        get() = "$folderJson$FOLDER_LANGUAGE"

    // Checks if a volume containing external storage is available
    // for read and write.
    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    // Checks if a volume containing external storage is available to at least read.
    private fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    fun createAppDirectories(ctx: Context) {
        var mExternalStorageAvailable = isExternalStorageReadable()
        var mExternalStorageWriteable = isExternalStorageWritable()
        if (mExternalStorageAvailable && mExternalStorageWriteable) {
            val externalStorageVolumes = ctx.filesDir //ContextCompat.getDataDir(ctx)
            appDataLocation = externalStorageVolumes?.absolutePath ?: ""
            var theDir = File(appDataLocation)
            if (!theDir.exists()) {
                theDir.mkdir()
            }

            theDir = File(folderOutbound)
            if (!theDir.exists()) {
                theDir.mkdir()
            }

            theDir = File(folderStandardimage)
            if (!theDir.exists()) {
                theDir.mkdir()
            }
            theDir = File(folderUsers)
            if (!theDir.exists()) {
                theDir.mkdir()
            }
            theDir = File(folderStores)
            if (!theDir.exists()) {
                theDir.mkdir()
            }
            theDir = File(folderActivities)
            if (!theDir.exists()) {
                theDir.mkdir()
            }
            theDir = File(folderCsv)
            if (!theDir.exists()) {
                theDir.mkdir()
            }
            theDir = File(folderMedia)
            if (!theDir.exists()) {
                theDir.mkdir()
            }
            theDir = File(folderIcons)
            if (!theDir.exists()) {
                theDir.mkdir()
            }
            theDir = File(folderJson)
            if (!theDir.exists()) {
                theDir.mkdir()
            }

            theDir = File(folderQuestiontemplate)
            if (!theDir.exists()) {
                theDir.mkdir()
            }
            theDir = File(folderConfig)
            if (!theDir.exists()) {
                theDir.mkdir()
            }

            theDir = File(folderLanguage)
            if (!theDir.exists()) {
                theDir.mkdir()
            }


            theDir = File(folderInbound)
            if (!theDir.exists()) {
                theDir.mkdir()
            }

            theDir = File(folderPhotos)
            if (!theDir.exists()) {
                theDir.mkdir()
            }

            theDir = File(folderSnaps)
            if (!theDir.exists()) {
                theDir.mkdir()
            }
            theDir = File(folderVisitor)
            if (!theDir.exists()) {
                theDir.mkdir()
            }
            theDir = File(folderAttendance)
            if (!theDir.exists()) {
                theDir.mkdir()
            }

            theDir = File(folderBatches)
            if (!theDir.exists()) {
                theDir.mkdir()
            }

        }
    }
}
