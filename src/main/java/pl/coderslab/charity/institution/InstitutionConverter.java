package pl.coderslab.charity.institution;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InstitutionConverter implements Converter<String,Institution> {
    private final InstitutionService institutionService;
    @Override
    public Institution convert(String source) {
        return institutionService.findById(Long.parseLong(source));
    }
}
