package com.nagwa.listing.domain.usecase

interface SuspendableUseCase<I,O> {
     fun execute(input: I?= null): O
}