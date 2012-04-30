package info.bytecraft.zones;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import info.bytecraft.zones.ZoneSettings;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.CONSTRUCTOR, ElementType.FIELD})
public @interface ZoneHandler {
	public abstract ZoneSettings settings();
}
