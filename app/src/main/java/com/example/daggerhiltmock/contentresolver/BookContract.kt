package com.example.daggerhiltmock.contentresolver

import android.net.Uri

object BookContract {
    const val CONTENT_PATH = "book"
    const val AUTHORITY = "com.example.contentprovidermock.provider.BookContentProvider"

    val CONTENT_URI: Uri = Uri.parse("content://${AUTHORITY}//${CONTENT_PATH}")
    const val BOOK_TABLE_NAME = "book"
    const val COLUMN_BOOK_NAME = "book_name"
    const val COLUMN_ID = "book_id"
    const val COLUMN_YEAR_OF_PUBLIsH = "year_of_publish"
}