package com.catail.bes_vision.home.bean;

public class UpdateBean {

	String versionCode;
	String updateUrl;
    private String force;
	private String g_version;
	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

	public String getG_version() {
		return g_version;
	}

	public void setG_version(String g_version) {
		this.g_version = g_version;
	}

	@Override
	public String toString() {
		return "UpdateBean{" +
				"versionCode='" + versionCode + '\'' +
				", updateUrl='" + updateUrl + '\'' +
				", force='" + force + '\'' +
				", g_version='" + g_version + '\'' +
				'}';
	}
}
