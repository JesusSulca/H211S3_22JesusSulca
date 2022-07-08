
package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("productoConverter")
public class ProductoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String tipo = "";
        if(value!=null){
            tipo = (String)value;
            switch(tipo){
                case "F" : tipo = "Familiar";break;
                case "I" : tipo = "Individual";break;
                case "X" : tipo = "XXL";break;
            }
        }
        return tipo;
    }
    
}
