package Mask;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface StockRepository extends PagingAndSortingRepository<Stock, Long>{

    Stock findByMaskType(String maskType);

}