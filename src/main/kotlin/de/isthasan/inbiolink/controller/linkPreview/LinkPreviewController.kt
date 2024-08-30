package de.isthasan.inbiolink.controller.linkPreview

import de.isthasan.inbiolink.model.dto.linkPreview.LinkPreviewResponse
import hostname.Hostname
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.commons.lang3.StringUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

@CrossOrigin
@RequestMapping
@RestController
@Tag(name = "Link Preview")
class LinkPreviewController {

    /**
     * Parses the web page and extracts the info from meta tags required for preview
     *
     * @param url
     * @return
     * @throws IOException
     */
    @GetMapping
    fun extractLinkPreviewInfo(url: String): LinkPreviewResponse {
        var targetUrl = url
        if (!url.startsWith("http")) {
            targetUrl = "http://$url"
        }
        val document: Document = Jsoup.connect(targetUrl)
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
            .get()
        // Get Open Graph meta tags
        val title: String = getMetaTagContent(document, "meta[name=title]") ?: getMetaTagContent(document, "title") ?: document.title()
        val desc: String = getMetaTagContent(document, "meta[name=description]") ?: getMetaTagContent(document, "description") ?: ""
        val ogUrl: String = StringUtils.defaultIfBlank(getMetaTagContent(document, "meta[property=og:url]"), url)
        val ogTitle: String = StringUtils.defaultIfBlank(getMetaTagContent(document, "meta[property=og:title]"), title)
        val ogDesc: String = StringUtils.defaultIfBlank(getMetaTagContent(document, "meta[property=og:description]"), desc)
        val ogImage: String = getMetaTagContent(document, "meta[property=og:image]") ?: ""
        val ogImageAlt: String = getMetaTagContent(document, "meta[property=og:image:alt]") ?: ""

        val domain = Hostname.getHostname(url)

        return LinkPreviewResponse(
            domain,
            url,
            ogUrl,
            StringUtils.defaultIfBlank(ogTitle, title),
            StringUtils.defaultIfBlank(ogDesc, desc),
            ogImage,
            ogImageAlt
        )
    }

    /**
     * Returns the given meta tag content
     *
     * @param document
     * @return
     */
    private fun getMetaTagContent(document: Document, cssQuery: String): String? {
        val elm: Element? = document.select(cssQuery).first()
        return elm?.attr("content")
    }
}