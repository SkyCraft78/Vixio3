package info.itsthesky.disky.tools.versions;

public interface VersionAdapter {

	Class<?> getColorClass();

	Object colorFromName(String name);

	String getColorName(Object color);
	
}
