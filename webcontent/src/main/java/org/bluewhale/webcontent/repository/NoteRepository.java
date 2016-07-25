package org.bluewhale.webcontent.repository;

import org.bluewhale.webcontent.beans.Note;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {

}
