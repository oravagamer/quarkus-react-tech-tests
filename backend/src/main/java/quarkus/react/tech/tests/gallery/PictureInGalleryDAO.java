package quarkus.react.tech.tests.gallery;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import quarkus.react.tech.tests.gallery.galleries.GalleryEntity;
import quarkus.react.tech.tests.gallery.pictures.PictureEntity;
import quarkus.react.tech.tests.gallery.pictures.PictureEntityDTO;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PictureInGalleryDAO implements PanacheRepository<PictureInGalleryEntity> {
    public List<PictureEntityDTO> findPicturesByGID(Long gid) {
        return getEntityManager()
                .createNamedQuery("PicturesOrderDAO.readPictureOrder", PictureEntityDTO.class)
                .setParameter(1, gid)
                .getResultList();
    }

    public void addPictureToGallery(Long pid, Long gid) {
        PictureInGalleryEntity
                .builder()
                .setId(
                        PictureInGalleryID
                                .builder()
                                .setPid(pid)
                                .setGid(gid)
                                .build()
                )
                .setPicture(PictureEntity.findById(pid))
                .setGallery(GalleryEntity.findById(gid))
                .setOrd(
                        ((Long) getEntityManager()
                                .createNamedQuery("PicturesOrderDAO.countPicturesInGallery")
                                .setParameter(1, gid)
                                .getResultList()
                                .get(0)) + 1
                )
                .build()
                .persist();
    }

    public List<PictureEntityDTO> reOrderPicturesInGallery(List<Long> pids, Long gid) {
        List<Long> list = new ArrayList<>(pids);
        EntityManager entityManager = getEntityManager();
        list.addAll(
                entityManager
                        .createNamedQuery("PicturesOrderDAO.getUnOrderedPictures")
                        .setParameter(1, gid)
                        .setParameter(2, pids)
                        .getResultList()
        );

        Log.info(list.toString());

        for (int i = 0; i < list.size(); i++) {
            entityManager
                    .createNamedQuery("PicturesOrderDAO.changeOrder")
                    .setParameter(1, i + 1)
                    .setParameter(2, list.get(i))
                    .setParameter(3, gid)
                    .executeUpdate();
        }
        return findPicturesByGID(gid);
    }
}
