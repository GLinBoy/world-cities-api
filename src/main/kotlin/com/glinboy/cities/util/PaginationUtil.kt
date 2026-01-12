package com.glinboy.cities.util

import org.springframework.data.domain.Page
import org.springframework.http.HttpHeaders
import org.springframework.web.util.UriComponentsBuilder

class PaginationUtil {
    companion object {
        fun <T: Any> generateHeaders(page: Page<T>, baseUrl: String ): HttpHeaders {
            val headers = HttpHeaders()
            headers.set("X-Total-Count", page.totalElements.toString())
            var link = ""
            if (page.number + 1 < page.totalPages) {
                link = "<" + generateUri(baseUrl, page.number + 1, page.size).toString() + ">; rel=\"next\","
            }
            if (page.number > 0) {
                link += "<" + generateUri(baseUrl, page.number - 1, page.size).toString() + ">; rel=\"prev\","
            }
            var lastPage = 0
            if (page.totalPages > 0) {
                lastPage = page.totalPages - 1
            }
            link += "<" + generateUri(baseUrl, lastPage, page.size).toString() + ">; rel=\"last\","
            link += "<" + generateUri(baseUrl, 0, page.size).toString() + ">; rel=\"first\""
            headers.add(HttpHeaders.LINK, link)
            return headers
        }

        private fun generateUri(baseUrl: String, page: Int, size: Int): String? = UriComponentsBuilder
            .fromUriString(baseUrl)
            .queryParam("page", page)
            .queryParam("size", size)
            .toUriString()
    }
}
