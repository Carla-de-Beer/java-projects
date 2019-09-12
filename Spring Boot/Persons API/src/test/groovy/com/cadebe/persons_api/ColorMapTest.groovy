package groovy.com.cadebe.persons_api

import com.cadebe.persons_api.util.ColorMap
import spock.lang.Specification
import spock.lang.Subject

class ColorMapTest extends Specification {

    @Subject
    def colorMap = new ColorMap()

    def "ColorMap getOrdinalFromEnum()"() {
        given:

        when: "calling getOrdinalFromEnum()"
        int result = ColorMap.getOrdinalFromEnum(color)

        then: "getOrdinalFromEnum() has been successfully called"
        result == expected

        where:
        color             | expected
        "Blue"            | 0
        "Green"           | 1
        "Purple"          | 2
        "Red"             | 3
        "Lemon yellow"    | 4
        "Turquoise"       | 5
        "White"           | 6
        "WHITE"           | 6
        "wHITe"           | 6
        "Some string ..." | -1
    }
}
