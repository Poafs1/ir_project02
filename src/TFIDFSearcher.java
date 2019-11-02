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
		List<Double> tf = new ArrayList<Double>();

//		query terms -> eg. [inform, retriev]
//		found tf -> 1 + Math.log10(freq of terms in each documents)
		for(int i=0; i<doc.size(); i++) {
			double getTF = 0.0;
			for(String q : queryTerms) {
				int countWord = Collections.frequency(doc.get(i).getTokens(), q);
				if(countWord != 0) getTF += 1.0 + Math.log10(new Double(countWord));
			}
			System.out.println("ID: " + doc.get(i).getId() + " TOKENS: " + doc.get(i).getTokens());
			System.out.println("TF Score: " + getTF);
			System.out.println();
			tf.add(getTF);
		}

//		found idf -> Math.log10(1 + (5999 / how many documents contains terms t))


		return null;
		/***********************************************/
	}
}

//int countWord = Collections.frequency(doc.get(i).getTokens(), q);