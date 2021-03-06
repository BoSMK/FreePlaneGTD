//=========================================================
// Freeplane GTD+
//
// Copyright (c)2014 Gergely Papp
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
//=========================================================
package freeplaneGTD.util

class Tag {
    String tagName
    def content = []
    Map params = [:]

    Tag(tagName, Map params = null) {
        this.tagName = tagName
        if (params) {
            this.params = params
        }
    }

    Tag(tagName, Object content, params = null) {
        this(tagName)
        if (content) {
            addContent(content.toString())
        }
        if (params) {
            this.params = params
        }
    }

    Tag(tagName, Tag content, params = null) {
        this(tagName)
        if (content) {
            addContent(content)
        }
        if (params) {
            this.params = params
        }
    }

    Tag addContent(Object content, Map params = null) {
        // Very simple sanitation for HTML entities <> here!
        this.content.add(content.toString().replaceAll('&', '&amp;').replaceAll('<', '&lt;').replaceAll('>', '&gt;'))
        if (params) {
            this.params = params
        }
        return this
    }

    Tag addPreformatted(String tag) {
        this.content.add(tag)
        return this
    }

    Tag addContent(Tag tag) {
        this.content.add(tag)
        return this
    }

    Tag addContent(tagName, Object content, Map params = null) {
        Tag tag = new Tag(tagName, content, params)
        this.content.add(tag)
        return this
    }

    Tag addChild(tagName, Map params = null) {
        Tag tag = new Tag(tagName, params)
        this.content.add(tag)
        return tag
    }

    Tag addProperty(String key, String value) {
        params.put(key, value)
    }

    String toString() {
        StringBuilder retval = new StringBuilder()
        retval.append('<').append(tagName)
        params.each {
            retval.append(' ')
            retval.append(it.key)
            retval.append('=\'')
            retval.append(it.value)
            retval.append('\'')
        }
        retval.append('>')
        content.each {
            retval.append(it)
        }
        retval.append('</').append(tagName).append('>')
        return retval.toString()
    }

}

