Naive Bayesian Text Classifier
==============================

The classifier is executed via Main.java. The main() method allows for the running of the classifier either as
* a single file at a time [runSingleBayes(...)], or
* multiple files as part of a batch process [batchClassification()].

Uncomment/comment out the relevant method required.

```Java
	// Either run the classifier against all of the files inside the given folder ...
	batchClassification();

	// ... or classify a single input file
	runSingleBayes(folderPathA, folderPathB, filePathX, categoryA, categoryB, A, B);
```
		
