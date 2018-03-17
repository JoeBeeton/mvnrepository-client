/**
 * Copyright © 2018 Reijhanniel Jearl Campos (devcsrj@apache.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package devcsrj.maven

import org.jsoup.nodes.Element
import pl.droidsonroids.jspoon.ElementConverter
import pl.droidsonroids.jspoon.annotation.Selector
import java.net.URI

internal class RepositoriesPage {

    @Selector("#maincontent > div.im")
    lateinit var entries: List<Entry>

    internal class Entry {

        @Selector("div.im-header > h2 > a", converter = IdConverter::class)
        lateinit var id: String

        @Selector("div.im-header > h2 > a")
        lateinit var name: String

        @Selector("div.im-header > p", converter = UriElementConverter::class)
        lateinit var uri: URI

        companion object IdConverter : ElementConverter<String> {

            override fun convert(node: Element, selector: Selector): String {
                val href = node.selectFirst(selector.value).attr("href")
                // hrefs are in the form of: /repos/{id}
                return href.substringAfterLast("/")
            }

        }
    }
}
