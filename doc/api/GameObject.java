
public interface GameObject {

	public void addProperty(Property propToAdd);
	
	public void removeProperty(Property propToRemove);
	
	public String getName();
	
	public void setName(String newName);
	
	public List<Property> getProperties();
	
	public void setImage(Image image);
	
	public Image getImage();
	
}
