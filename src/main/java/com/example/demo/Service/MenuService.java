package com.example.demo.Service;

import com.example.demo.entity.Boisson;
import com.example.demo.entity.Menu;
import com.example.demo.repo.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }



    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }
    public Menu editeMenu(Menu menu, long id) {
        Optional<Menu> menuOptional = menuRepository.findById(id);

        if (menuOptional.isPresent()) {
            Menu existingMenu = menuOptional.get();
            existingMenu.setNom(menu.getNom());
            existingMenu.setDescription(menu.getDescription());
            return menuRepository.save(existingMenu); // Enregistre les modifications
        } else {
            throw new RuntimeException("Menu non trouvé avec l'ID : " + id);
        }
    }
    public void deleteMenu(long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
        } else {
            throw new RuntimeException("Menu non trouvé avec l'ID : " + id);
        }
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu non trouvé avec l'id : " + id));
    }

}
