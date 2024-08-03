package ir.reyreey.myspringexample.service;

import ir.reyreey.myspringexample.repository.AuthorityRrepository;
import ir.reyreey.myspringexample.repository.entities.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/28/2024, Sunday
 **/
@Service
public class DefaultAuthorityService implements AuthorityService{
    @Autowired
    private AuthorityRrepository authorityRrepository;

    @Override
    public Authority insertAuthority(Authority authority) {
        return authorityRrepository.save(authority);
    }
}
