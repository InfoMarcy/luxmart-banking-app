package com.openshift.model;

public class DatosTelefonicos {

    	
    	
    	public DatosTelefonicos() {
		super();
	}
		public DatosTelefonicos(String extension, String compania) {
		super();
		this.extension = extension;
		this.compania = compania;
	}
    	
		
		private String extension;
        private String compania;
        
		public String getExtension() {
			return extension;
		}
		public void setExtension(String extension) {
			this.extension = extension;
		}
		public String getCompania() {
			return compania;
		}
		public void setCompania(String compania) {
			this.compania = compania;
		}
        
        

}
