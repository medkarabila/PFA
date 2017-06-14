package ma.ensao.entity;

public class MatiereModule {

	private Integer id;
	private Matiere matiere;
	private Module module;
	
	
	
	public MatiereModule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MatiereModule(Matiere matiere, Module module) {
		super();
		this.matiere = matiere;
		this.module = module;
	}
	public MatiereModule(Module module,Matiere matiere) {
		super();
		this.matiere = matiere;
		this.module = module;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
}
