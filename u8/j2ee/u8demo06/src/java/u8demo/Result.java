package u8demo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result {
    private int area;
    private int perimeter;

    @XmlElement
    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @XmlElement
    public int getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }
    
}