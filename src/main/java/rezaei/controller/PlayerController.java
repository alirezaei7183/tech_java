package rezaei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rezaei.Greeting;
import rezaei.exception.NewsNonFoundException;
import rezaei.model.Player;
import rezaei.model.Score;
import rezaei.repository.PlayerRepository;
import rezaei.sevice.PlayerService;
import rezaei.sevice.errorResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.*;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerService playerService;


    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private Long plaI;
    private Map<String, Object> response;
    private static final Logger LOGGER = Logger.getLogger(PlayerController.class.getName());

    @GetMapping("/index")
    public List<Player> sayHello() {

        return playerRepository.findAll();

    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @PostMapping("/game/score1")
    public Player createPlayer(@RequestBody Player player) {
        Player game = playerRepository.save(player);

        return new Player(game.getPlayerId(), game.getScore(), game.getRank());

    }

    @PostMapping("/game/score")
    public Map<String, Object> createPlayerScore(@RequestBody Player player) throws errorResult, IOException {
        // return playerRepository.save(player);

        long playerId = player.getPlayerId();
        int score = player.getScore();
        int rank = player.getRank();
        long timestamp = player.getTimestamp();
        Optional<Player> players = playerService.getPlayerId(playerId);
        if (score > 100 || score < 0) {
            throw new NewsNonFoundException(String.valueOf(score));
        }
        if (players.isPresent()) {
            int BeforeScore = players.get().getScore();
            int newScore = BeforeScore + score;
            playerService.insertPlyer(new Player(playerId, newScore, rank, timestamp));
            return playerService.customResponseInsertPlayer(playerId, newScore, timestamp);
        } else {
            Player allField = playerService.insertPlyer(player);

            FileHandler fileHandler = new FileHandler("./log.txt");

            // Creating SimpleFormatter
            Formatter simpleFormatter = new SimpleFormatter();

            // Assigning handler to logger
            LOGGER.addHandler(fileHandler);

            // Logging message of Level info (this should be publish in the default format i.e. XMLFormat)
            LOGGER.info("method:POST------ url:game/score");


            return playerService.customResponseInsertPlayer(allField.getPlayerId(), allField.getScore(), allField.getTimestamp());

        }
    }

    @GetMapping("game/score")
    public Score retrieveStudent(
            @RequestParam(value = "playerid", defaultValue = "1") long id,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "100") Integer pageSize,
            @RequestParam(defaultValue = "score") String sortBy) throws IOException {
        ArrayList list = new ArrayList();
        Optional<Player> players = playerRepository.findById(id);
        if (!players.isPresent())
            throw new NewsNonFoundException("id=" + id);
        long playerId = players.get().getPlayerId();
        int score = players.get().getScore();
        int rank = players.get().getRank();
        // List<Player> allRecords = playerRepository.findAll();
        List<Player> allRecords = playerService.getAllPlayer(pageNo, pageSize, sortBy);
        for (int i = 0; i < allRecords.size(); i++) {
            long plyerIdRecords = allRecords.get(i).getPlayerId();
            int scoreRecords = allRecords.get(i).getScore();
            int rankRecords = allRecords.get(i).getRank();
            response = playerService.customResponseGetPlayer(plyerIdRecords, scoreRecords, rankRecords);
            list.add(response);
        }
        //return  list;

        // Logging message of Level info (this should be publish in the default format i.e. XMLFormat)
        LOGGER.info("method:GET------ url:game/score");
        return new Score(playerId, score, rank, list);


        //return playerRepository.save(player);

    }

}