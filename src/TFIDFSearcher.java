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

		for(String q : queryTerms) {
			double getIDF = 0.0;
			int df = 0;
			for(int i=0; i<doc.size(); i++) {
				if(doc.get(i).getTokens().contains(q)) df++;
			}
			getIDF = Math.log10(1 + (doc.size() / df));

//			terms frequency (TF) -> 1 + log10(number of terms in document x)
			System.out.println("Term: " + q);
			for(int i=0; i<doc.size(); i++) {
				double getTF = 0.0;
				int countWord = Collections.frequency(doc.get(i).getTokens(), q);
//				TF of each document x that contains query terms (q)
				if(countWord != 0) getTF = 1 + Math.log10(countWord);
				double getTFIDF = getTF * getIDF;
				System.out.print(getTFIDF + " -- ");
			}
			System.out.println();
		}

		System.out.println();
		return null;
		/***********************************************/
	}
}