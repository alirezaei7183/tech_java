package rezaei.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rezaei.model.Player;
import rezaei.repository.PlayerRepository;

import java.util.*;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Optional<Player> getPlayerId(long id){
        return playerRepository.findById(id);
    }

    public Player insertPlyer(Player player){

        return playerRepository.save(player);
    }

    public Map<String, Object> customResponseInsertPlayer(long playerId, int score, long timestamp) {

        Map<String, Object> map = new HashMap<>();
        map.put("playerId", playerId);
        map.put("score", score);
        map.put("timestamp", timestamp);
        return map;
    }
    public Map<String, Object> customResponseGetPlayer(long playerId, int score, int rank){

            Map<String,Object> map=new HashMap<>();
            map.put("playerId",playerId);
            map.put("score",score);
            map.put("rank",rank);
            return map;
    }

    public List<Player> getAllPlayer(int pageNum, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy).descending());
        Page<Player> pagedResult = playerRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Player>();
        }

    }

}
