package ma.ensao.entity;

public class Chapitre {
	
	
	private int id ; 
	private String chapitre ;
	private String description;
	private String question;
	private String fichier;
	private Matiere matiere;
	private boolean archiver ; 
	
	public boolean isArchiver() {
		return archiver;
	}
	public void setArchiver(boolean archiver) {
		this.archiver = archiver;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChapitre() {
		return chapitre;
	}
	public void setChapitre(String chapitre) {
		this.chapitre = chapitre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getFichier() {
		return fichier;
	}
	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public Chapitre(int id, String chapitre, String description, String question, String fichier, Matiere matiere , boolean archiver) {
		super();
		this.id = id;
		this.chapitre = chapitre;
		this.description = description;
		this.question = question;
		this.fichier = fichier;
		this.matiere = matiere;
		this.archiver=archiver;
	}
	public Chapitre() {
		super();
	}
	
	

}
