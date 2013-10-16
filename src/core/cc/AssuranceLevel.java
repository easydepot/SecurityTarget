package core.cc;

import java.util.ArrayList;


	public class AssuranceLevel {
		String level;
		ArrayList<String> augmentation = new ArrayList<String>();
		String qualificationLevel;
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		
		public boolean addAugmentation(String e) {
			return augmentation.add(e);
		}
		public String getAugmentation(int index) {
			return augmentation.get(index);
		}
		public boolean isAugmented() {
			return !augmentation.isEmpty();
		}
		public int numberOfAugmentation() {
			return augmentation.size();
		}
		public String getQualificationLevel() {
			return qualificationLevel;
		}
		public void setQualificationLevel(String qualificationLevel) {
			this.qualificationLevel = qualificationLevel;
		}
		
		
	}

