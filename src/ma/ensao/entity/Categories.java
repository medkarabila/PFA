package ma.ensao.entity;

public class Categories {

	private Integer id;
	private String categorie;
	private String description;
	private String icon;
	private boolean archiver;
	
	public boolean isArchiver() {
		return archiver;
	}
	public void setArchiver(boolean archiver) {
		this.archiver = archiver;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Categories(Integer id, String categorie, String description,
			String icon,boolean archiver) {
		super();
		this.id = id;
		this.categorie = categorie;
		this.description = description;
		this.icon = icon;
		this.archiver=archiver;
	}
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
