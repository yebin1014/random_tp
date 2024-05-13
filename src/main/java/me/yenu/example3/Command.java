package me.yenu.example3;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static org.bukkit.Material.*;

public class Command extends BukkitCommand {

    public Command(@NotNull String name) {
        super(name);
    }


    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {

            World world = player.getWorld();

            int mx = (int) (Math.random() * 20001) - 10000; // 좌표 찾기
            int mz = (int) (Math.random() * 20001) - 10000;

            List<Location> arList = findSafeLocations(world, mx, mz); // 안전한 장소를 찾은 후 리스트에 추가

            if (!arList.isEmpty()) { // 플레이어 이동 /
                Location random = arList.get((int) (Math.random() * arList.size())); // 리스트가 비어있는지 확인 후 안전한 곳이 없다면 else 구문 실행
                player.teleport(random); // 비어있지 않다면 안전한 곳 중 무작위 위치 이동
            } else {
                commandSender.sendMessage("알아서 찾아");
            }
        }
        return false;
    }
    private boolean isSafeBlock(Material material) { // 안전한 블럭 찾기
        return material == AIR || material == WATER; // 공기와 물이라면 안전하다고 판단
    }

    // 안전한 장소 찾기
    private List<Location> findSafeLocations(World world, int mx, int mz) {
        List<Location> safeLocations = new ArrayList<>(); // 빈 array list 생성
        for (int y = world.getHighestBlockYAt(mx, mz) + 1; y >= world.getMinHeight(); y--) { // 블럭 최대높이 부터 최소 높이까지 검사
            Location location = new Location(world, mx, y, mz); // x 와 z 는 고정후 y만 변경
            Block block = location.getBlock();
            if (isSafeBlock(block.getType())) { // 블럭이 안전한지 확인
                safeLocations.add(location); // 안전하다면 리스트에 추가
            }
        } return safeLocations;
    }
}
