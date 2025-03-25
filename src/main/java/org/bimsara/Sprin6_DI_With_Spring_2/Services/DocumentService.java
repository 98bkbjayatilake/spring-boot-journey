package org.bimsara.Sprin6_DI_With_Spring_2.Services;

/*We will have two profiles:"dev" and "prod".The 'dev' profile will write a simple text document with a basic message ,
 *while the "prod" profile will create a more structured word document with additional formatting
 */
public interface DocumentService {
    void createDocument(String content);
}
