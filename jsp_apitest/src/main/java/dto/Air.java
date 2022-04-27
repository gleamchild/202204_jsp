package dto;

import java.util.Date;

public class Air {
	private String sn;
	private Date dataDate;
	private String districtName;
	private String moveName;
	private String itemCode;
	private String issueGbn;
	private Date issueDate;
	private String issueTime;
	private String issueVal;
	private Date clearDate;
	private String clearTime;
	private String clearVal;
	public Air() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Air(String sn, Date dataDate, String districtName, String moveName, String itemCode, String issueGbn,
			Date issueDate, String issueTime, String issueVal, Date clearDate, String clearTime, String clearVal) {
		super();
		this.sn = sn;
		this.dataDate = dataDate;
		this.districtName = districtName;
		this.moveName = moveName;
		this.itemCode = itemCode;
		this.issueGbn = issueGbn;
		this.issueDate = issueDate;
		this.issueTime = issueTime;
		this.issueVal = issueVal;
		this.clearDate = clearDate;
		this.clearTime = clearTime;
		this.clearVal = clearVal;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getMoveName() {
		return moveName;
	}
	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getIssueGbn() {
		return issueGbn;
	}
	public void setIssueGbn(String issueGbn) {
		this.issueGbn = issueGbn;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public String getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}
	public String getIssueVal() {
		return issueVal;
	}
	public void setIssueVal(String issueVal) {
		this.issueVal = issueVal;
	}
	public Date getClearDate() {
		return clearDate;
	}
	public void setClearDate(Date clearDate) {
		this.clearDate = clearDate;
	}
	public String getClearTime() {
		return clearTime;
	}
	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}
	public String getClearVal() {
		return clearVal;
	}
	public void setClearVal(String clearVal) {
		this.clearVal = clearVal;
	}
	@Override
	public String toString() {
		return "Air [sn=" + sn + ", dataDate=" + dataDate + ", districtName=" + districtName + ", moveName=" + moveName
				+ ", itemCode=" + itemCode + ", issueGbn=" + issueGbn + ", issueDate=" + issueDate + ", issueTime="
				+ issueTime + ", issueVal=" + issueVal + ", clearDate=" + clearDate + ", clearTime=" + clearTime
				+ ", clearVal=" + clearVal + "]";
	}
}
