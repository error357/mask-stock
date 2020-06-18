package Mask;

import Mask.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    StockRepository stockRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_Deliver(@Payload Ordered ordered){

        if(ordered.isMe()){

            if(stockRepository.findByMaskType(ordered.getMaskType()) == null) {
                Stock stock = new Stock();

//                if(ordered.getMaskType() == "KF10") {
//                    stock.setId((long) 11);
//                }
//                else {
//                    if (ordered.getMaskType() == "KF20") {
//                        stock.setId((long) 22);
//                    } else {
//                        if (ordered.getMaskType() == "KF30") {
//                            stock.setId((long) 33);
//                        } else {
//                            stock.setId((long) 99);
//                        }
//                    }
//                }

                stock.setMaskType(ordered.getMaskType());
                stock.setQty(-ordered.getQty());

                stockRepository.save(stock);
            }
            else {
                Stock stock = stockRepository.findByMaskType(ordered.getMaskType());
                stock.setQty(stock.getQty() - ordered.getQty());

                stockRepository.save(stock);
            }

            System.out.println("##### listener Deliver : " + ordered.toJson());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturned_Deliver(@Payload Returned returned){

        if(returned.isMe()){

            if(stockRepository.findByMaskType(returned.getMaskType()) == null) {
                Stock stock = new Stock();

            //    if(returned.getMaskType().equals("KF10")) {
            //        stock.setId((long) 11);
            //    }
            //    else {
            //        if(returned.getMaskType().equals("KF20")) {
            //            stock.setId((long) 22);
            //        } else {
            //            if(returned.getMaskType().equals("KF30")) {
            //               stock.setId((long) 33);
            //           }
            //            else {
            //                stock.setId((long) 99);
            //            }
            //        }
            //    }

                stock.setMaskType(returned.getMaskType());
                stock.setQty(-returned.getQty());

                stockRepository.save(stock);
            }
            else {
                Stock stock = stockRepository.findByMaskType(returned.getMaskType());
                stock.setQty(stock.getQty() - returned.getQty());

                stockRepository.save(stock);
            }

            System.out.println("##### listener Deliver : " + returned.toJson());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPut_Store(@Payload Put put){

        if(put.isMe()){

            if(stockRepository.findByMaskType(put.getMaskType()) == null) {
                Stock stock = new Stock();
//
//                if(put.getMaskType() == "KF10") {
//                    stock.setId((long) 11);
//                }
//                else {
//                    if(put.getMaskType() == "KF20") {
//                        stock.setId((long) 22);
//                    }
//                    else {
//                        if (put.getMaskType() == "KF30") {
//                            stock.setId((long) 33);
//                        } else {
//                            stock.setId((long) 99);
//                        }
//                    }
//                }

                stock.setMaskType(put.getMaskType());
                stock.setQty(put.getQty());

                stockRepository.save(stock);
            }
            else {
                Stock stock = stockRepository.findByMaskType(put.getMaskType());
                stock.setQty(stock.getQty() + put.getQty());

                stockRepository.save(stock);
            }

            System.out.println("##### listener Store : " + put.toJson());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCancelled_Store(@Payload Cancelled cancelled){

        if(cancelled.isMe()){

            if(stockRepository.findByMaskType(cancelled.getMaskType()) == null) {
                Stock stock = new Stock();

//                if(cancelled.getMaskType() == "KF10") {
//                    stock.setId((long) 11);
//                }
//                else {
//                    if(cancelled.getMaskType() == "KF20") {
//                        stock.setId((long) 22);
//                    }
//                    else {
//                        if (cancelled.getMaskType() == "KF30") {
//                            stock.setId((long) 33);
//                        } else {
//                            stock.setId((long) 99);
//                        }
//                    }
//                }

                stock.setMaskType(cancelled.getMaskType());
                stock.setQty(cancelled.getQty());

                stockRepository.save(stock);
            }
            else {
                Stock stock = stockRepository.findByMaskType(cancelled.getMaskType());
                stock.setQty(stock.getQty() + cancelled.getQty());

                stockRepository.save(stock);
            }

            System.out.println("##### listener Store : " + cancelled.toJson());
        }
    }

}
