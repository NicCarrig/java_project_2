package adapter;

import exception.*;

public interface UpdateAuto {
	public void updateSubmodelName(String modelName, String submodelName, String newName);
	public void updateOptionPrice(String modelName, String submodelName, String optionName, double newPrice);
}
