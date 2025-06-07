package pl.krzysztofskul.cadmdb.device.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import pl.krzysztofskul.cadmdb.device.Device;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;

	private String namePL;
	private String nameEN;
	
	private String namePLplural;
	private String nameENplural;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
	private Category categoryParent;
	
    @OneToMany(
            mappedBy = "categoryParent",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
        )
    private List<Category> categoryChildren = new ArrayList<Category>();
    
	@OneToMany(mappedBy = "category")
	private List<Device> deviceList = new ArrayList<Device>();

	/**
	 * Constructor
	 */
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param code
	 * @param namePL
	 * @param nameEN
	 * @param namePLplural
	 * @param nameENplural
	 */
	public Category(String code, String namePL, String nameEN, String namePLplural, String nameENplural) {
		super();
		this.code = code;
		this.namePL = namePL;
		this.nameEN = nameEN;
		this.namePLplural = namePLplural;
		this.nameENplural = nameENplural;
	}

	/*
	 * Getters and setters
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamePL() {
		return namePL;
	}

	public void setNamePL(String namePL) {
		this.namePL = namePL;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getNamePLplural() {
		return namePLplural;
	}

	public void setNamePLplural(String namePLplural) {
		this.namePLplural = namePLplural;
	}

	public String getNameENplural() {
		return nameENplural;
	}

	public void setNameENplural(String nameENplural) {
		this.nameENplural = nameENplural;
	}

	public List<Device> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Category getCategoryParent() {
		return categoryParent;
	}

	public void setCategoryParent(Category categoryParent) {
		this.categoryParent = categoryParent;
	}

	public List<Category> getCategoryChildren() {
		return categoryChildren;
	}

	public void setCategoryChildren(List<Category> categoryChildren) {
		this.categoryChildren = categoryChildren;
	}
	
	/*
	 * Additional methods
	 */

    public void addChildCategory(Category child) {
        if (child == null) return;
        child.setCategoryParent(this);
        this.categoryChildren.add(child);
    }

    public void removeChildCategory(Category child) {
        if (child == null) return;
        if (this.categoryChildren.remove(child)) {
            child.setCategoryParent(null);
        }
    }
	
	public void addDevice(Device device) {
		this.deviceList.add(device);
		device.setCategory(this);
	}
	
	public void removeDevice(Device device) {
		this.deviceList.remove(device);
		device.setCategory(null);
	}
}
