package pl.krzysztofskul.cadmdb2.init;

import java.util.List;

public interface InitDataGenerator<T> {
	
	T initDataAndReturn();
	
	List<T> iniListAndReturn();

}
