package hobbiedo.chat.mongoInfrastructure;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import hobbiedo.chat.domain.Chat;

public interface ChatRepository extends MongoRepository<Chat, String> {
	@Query("{ 'crewId': ?0, 'createdAt': { '$lt': ?1 } }")
	List<Chat> findByCrewIdAndCreatedAtAfter(Long crewId, Instant lastReadAt, Pageable pageable);

	@Query("{ 'crewId': ?0, 'imageUrl': { '$exists': true } }")
	List<Chat> findByCrewIdAndImageUrl(Long crewId);

	@Query(value = "{ 'imageUrl': { '$exists': true }, 'createdAt': { '$lt': ?0 } }", delete = true)
	void deleteByImageUrlExistsAndCreatedAtBefore(Instant expiryDate);
}
