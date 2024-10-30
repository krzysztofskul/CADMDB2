package pl.krzysztofskul.cadmdb.init;

import java.util.List;

public interface InitDataGenerator<T> {
	
	T initDataAndReturn();
	
	List<T> iniListAndReturn();

}
