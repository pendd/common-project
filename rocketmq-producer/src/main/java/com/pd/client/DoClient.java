package com.pd.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

/**
 * @author YCWB0382
 * @date 2023-06-29 14:51
 */
@Component
@Slf4j
public class DoClient implements CommandLineRunner {

 private final ProducerClient producerClient;

 public DoClient(ProducerClient producerClient) {
  this.producerClient = producerClient;
 }


 @Override
 public void run(String... args) {
     pushMessage();
 }

    private void pushMessage() {
        final LocalDateTime localDateTime = LocalDateTime.of(2023, 6, 29, 12, 10, 0);

        String temperatureMsg =
                """
                {"data":{"85957":"%s","85958":"%s","85955":"BF4","85956":"铁水温度"},"gatewayTime":1687936708209,"groupId":115,"groupName":"高炉变量","sourceMsgTime":null}
                """;

        String siMsg =
                """
                {"data":{"85957":"%s","85958":"%s","85955":"BF4","85956":"高炉铁水-Si"},"gatewayTime":1687936708209,"groupId":115,"groupName":"高炉变量","sourceMsgTime":null}
                """;

        String siYcMsg =
                """
                {"data":{"85957":"%s","85958":"%s","85955":"BF4","85956":"预测Si"},"gatewayTime":1687936708209,"groupId":115,"groupName":"高炉变量","sourceMsgTime":null}
                """;

        IntStream.range(1, 20)
                .forEach(item -> {
                 producerClient.sendMessage(String.format(temperatureMsg, BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.UP), localDateTime.plusMinutes(item).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                 producerClient.sendMessage(String.format(siMsg, BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.UP), localDateTime.plusMinutes(item).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                 producerClient.sendMessage(String.format(siYcMsg, BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.UP), localDateTime.plusMinutes(item).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                });
    }

    public static void main(String[] args) {
  System.out.println(BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.UP));
  System.out.println(LocalDateTime.of(2023, 6, 29, 12, 10, 0).plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
 }
}
