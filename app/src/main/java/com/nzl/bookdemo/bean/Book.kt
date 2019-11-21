package com.nzl.bookdemo.bean

import java.math.BigDecimal

/**
 * FileName:   Book
 * Author:     nizonglong
 * CreateTime: 2019/11/21 15:43
 */
data class Book(
    var bookid: String = "",
    var bookName: String = "",
    var bookDesc: String = "",
    var bookPrice: BigDecimal = BigDecimal.ZERO,
    var bookMainPic: String = "",
    var bookAuthor: String = "",
    var bookChapter: Int = 0
) 