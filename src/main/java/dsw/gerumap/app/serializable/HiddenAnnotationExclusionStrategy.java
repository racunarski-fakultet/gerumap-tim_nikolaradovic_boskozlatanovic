package dsw.gerumap.app.serializable;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class HiddenAnnotationExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fieldAttributes.getAnnotation(Hide.class) !=null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
