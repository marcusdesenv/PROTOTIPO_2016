package seguranca.mb;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import seguranca.dao.TbOpcaoDAO;
import seguranca.model.TbOpcao;

@FacesConverter(value = "convertTbOpcao", forClass = TbOpcao.class)
public class ConvertTbOpcao implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        if (value != null) {
            return new TbOpcaoDAO().consultarPorIdt(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {
        if (object != null && object instanceof TbOpcao) {
            return String.valueOf(((TbOpcao) object).getIdtOpcao());
        }
        return null;
    }
}
