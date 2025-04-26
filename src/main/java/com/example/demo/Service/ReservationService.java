package com.example.demo.Service;

import com.example.demo.entity.Reservation;
import com.example.demo.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private MailService mailService;
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Reservation reservation) {
        // Vérifier si la table est déjà réservée au même créneau
        Optional<Reservation> existingReservation = reservationRepository.findByDateHeureAndNumeroTable(
                reservation.getDateHeure(), reservation.getNumeroTable());

        if (existingReservation.isPresent()) {
            throw new RuntimeException("La table " + reservation.getNumeroTable() + " est déjà réservée à cette heure !");
        }

        Reservation savedReservation = reservationRepository.save(reservation);

        mailService.envoyerConfirmation(
                reservation.getEmail(), // ou savedReservation.getEmail()
                "Confirmation de réservation",
                "Votre réservation a bien été enregistrée pour le " + reservation.getDateHeure()
        );

        return savedReservation;
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setNomClient(reservationDetails.getNomClient());
                    reservation.setDateHeure(reservationDetails.getDateHeure());
                    reservation.setNombrePersonnes(reservationDetails.getNombrePersonnes());
                    reservation.setNumeroTable(reservationDetails.getNumeroTable());

                    // Vérifier si la table est déjà réservée pour ce nouveau créneau
                    Optional<Reservation> conflictingReservation = reservationRepository.findByDateHeureAndNumeroTable(
                            reservation.getDateHeure(), reservation.getNumeroTable());

                    if (conflictingReservation.isPresent() && !conflictingReservation.get().getId().equals(id)) {
                        throw new RuntimeException("La table " + reservation.getNumeroTable() + " est déjà réservée à cette heure !");
                    }

                    return reservationRepository.save(reservation);
                })
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
