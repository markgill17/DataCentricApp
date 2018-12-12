package Neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import static org.neo4j.driver.v1.Values.parameters;

public class Main {

	public static void createNodes(String name, String address) {
		Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4jdb"));
		Session session = driver.session();
		session.writeTransaction(new TransactionWork<String>() {
			@Override
			public String execute(Transaction tx) {
				tx.run("CREATE (:Student {name:{name}})", parameters("name", name));
				tx.run("CREATE (:Address {name:{address}})", parameters("address", address));
				return null;
			}
		});

	}
}