package ma.ensao.entity;

public class Matiere {

	private Integer id;
	private String matiere;
	private String description;
	private String icon;
	private boolean archiver;
	private Integer editeur;
	
	
	
	public boolean isArchiver() {
		return archiver;
	}
	public void setArchiver(boolean archiver) {
		this.archiver = archiver;
	}
	public Matiere() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Matiere(Integer id, String matiere, String description, String icon, boolean archiver,Integer editeur) {
		super();
		this.id = id;
		this.matiere = matiere;
		this.description = description;
		this.icon = icon;
		this.archiver=archiver;
		this.editeur=editeur;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMatiere() {
		return matiere;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
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
	public Integer getEditeur() {
		return editeur;
	}
	public void setEditeur(Integer editeur) {
		this.editeur = editeur;
	}
	
	
}
