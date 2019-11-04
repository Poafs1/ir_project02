//Name: Rattanavaree Muangamai - Pantita Wang - Prach Yothaprasert
//Section: 3
//ID: 6088092 - 6088219 - 6088234

import java.util.*;

public class TFIDFSearcher extends Searcher
{
	private List<Document> doc;
	public TFIDFSearcher(String docFilename) {
		super(docFilename);
		/************* YOUR CODE HERE ******************/
		this.doc = this.documents;
		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		List<String> tokens = Searcher.tokenize(queryString);
		Set<String> queryTerms = new HashSet<String>(tokens);
		List<Double> tfidf = new ArrayList<Double>();

		for(String q : queryTerms) {
//			find tf and idf of each query
//			find idf of each terms
			int df = 0;
			for(int i=0; i<doc.size(); i++) {
				if(doc.get(i).getTokens().contains(q)) df++;
			}
			double calIDF = Math.log10(1 + (doc.size() / df));

//			find tf in each documents
			for(int i=0; i<doc.size(); i++) {
//				terms q in each documents
				int countWord = Collections.frequency(doc.get(i).getTokens(), q);
				double calTF = 1 + Math.log10(countWord);
				double calTFIDF = calTF * calIDF;
//				System.out.println(calTFIDF);
			}
		}

		System.out.println();
		return null;
		/***********************************************/
	}
}